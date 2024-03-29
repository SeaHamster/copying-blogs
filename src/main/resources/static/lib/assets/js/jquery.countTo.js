(function (a) {
    if (typeof define === "function" && define.amd) {
        define(["jquery"], a)
    } else {
        if (typeof exports === "object") {
            a(require("jquery"))
        } else {
            a(jQuery)
        }
    }
}(function (a) {
    var b = function (d, e) {
        this.$element = a(d);
        this.options = a.extend({}, b.DEFAULTS, this.dataOptions(), e);
        this.init()
    };
    b.DEFAULTS = {
        from: 0,
        to: 0,
        speed: 1000,
        refreshInterval: 100,
        decimals: 0,
        formatter: c,
        onUpdate: null,
        onComplete: null
    };
    b.prototype.init = function () {
        this.value = this.options.from;
        this.loops = Math.ceil(this.options.speed / this.options.refreshInterval);
        this.loopCount = 0;
        this.increment = (this.options.to - this.options.from) / this.loops
    };
    b.prototype.dataOptions = function () {
        var g = {
            from: this.$element.data("from"),
            to: this.$element.data("to"),
            speed: this.$element.data("speed"),
            refreshInterval: this.$element.data("refresh-interval"),
            decimals: this.$element.data("decimals")
        };
        var f = Object.keys(g);
        for (var d in f) {
            var e = f[d];
            if (typeof (g[e]) === "undefined") {
                delete g[e]
            }
        }
        return g
    };
    b.prototype.update = function () {
        this.value += this.increment;
        this.loopCount++;
        this.render();
        if (typeof (this.options.onUpdate) == "function") {
            this.options.onUpdate.call(this.$element, this.value)
        }
        if (this.loopCount >= this.loops) {
            clearInterval(this.interval);
            this.value = this.options.to;
            if (typeof (this.options.onComplete) == "function") {
                this.options.onComplete.call(this.$element, this.value)
            }
        }
    };
    b.prototype.render = function () {
        var d = this.options.formatter.call(this.$element, this.value, this.options);
        this.$element.text(d)
    };
    b.prototype.restart = function () {
        this.stop();
        this.init();
        this.start()
    };
    b.prototype.start = function () {
        this.stop();
        this.render();
        this.interval = setInterval(this.update.bind(this), this.options.refreshInterval)
    };
    b.prototype.stop = function () {
        if (this.interval) {
            clearInterval(this.interval)
        }
    };
    b.prototype.toggle = function () {
        if (this.interval) {
            this.stop()
        } else {
            this.start()
        }
    };

    function c(e, d) {
        return e.toFixed(d.decimals)
    }

    a.fn.countTo = function (d) {
        return this.each(function () {
            var e = a(this);
            var f = e.data("countTo");
            var g = !f || typeof (d) === "object";
            var i = typeof (d) === "object" ? d : {};
            var h = typeof (d) === "string" ? d : "start";
            if (g) {
                if (f) {
                    f.stop()
                }
                e.data("countTo", f = new b(this, i))
            }
            f[h].call(f)
        })
    }
}));